package com.zhangyingwei.cockroach.queue;


import com.zhangyingwei.cockroach.config.Constants;
import com.zhangyingwei.cockroach.executer.task.Task;
import com.zhangyingwei.cockroach.executer.task.TaskCompatator;
import com.zhangyingwei.cockroach.queue.filter.IQueueTaskFilter;
import com.zhangyingwei.cockroach.queue.filter.TaskFilterBox;
import org.apache.log4j.Logger;
import java.util.List;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.PriorityBlockingQueue;

/**
 * Created by zhangyw on 2017/8/10.
 * 消息队列
 */
public class TaskQueue implements CockroachQueue {
    private Logger logger = Logger.getLogger(TaskQueue.class);

    private BlockingQueue<Task> queue;
    private BlockingQueue<Task> faildQueue;

    private TaskFilterBox filterBox;

    public static TaskQueue of(){
        return TaskQueue.of(Constants.DEFAULT_QUEUE_CALACITY);
    }

    public static TaskQueue of(int calacity){
        return new TaskQueue(calacity);
    }

    public TaskQueue(Integer calacity) {
        this.queue = new PriorityBlockingQueue<Task>(calacity,new TaskCompatator());
        this.faildQueue = new PriorityBlockingQueue<Task>();
        this.filterBox = new TaskFilterBox();
        logger.info("create queue whith calacity " + calacity);
    }

    @Override
    public Task poll() throws InterruptedException {
        this.queueValid();
        Task task = this.queue.poll();
        logger.info(Thread.currentThread().getName() + " pull task " + task);
        return task;
    }

    @Override
    public Task take() throws InterruptedException {
        this.queueValid();
        Task task = this.queue.take();
        logger.info(Thread.currentThread().getName() + " take task " + task);
        return task;
    }

    /**
     * 如果 queue 为空
     * 如果 task retry 次数小于系统设置的 DEFAULT_TASK_RESTY
     * 把失败任务重新添加到队列中
     */
    private synchronized void queueValid() throws InterruptedException {
        if(this.queue.isEmpty() && !this.faildQueue.isEmpty()){
            for (Task task : this.faildQueue) {
                this.push(task, false);
            }
            this.faildQueue.clear();
        }
        if (this.queue.isEmpty()) {
            logger.info(Thread.currentThread().getName() + " queue is empty");
        }
    }

    @Override
    public void push(Task task) throws InterruptedException {
        this.push(task, true);
    }

    @Override
    public void push(Task task, Boolean withFilter) throws InterruptedException {
        if (withFilter) {
            if (filterBox.accept(task)) {
                this.queue.put(task);
            }
        }else {
            this.queue.put(task);
        }
        logger.info(Thread.currentThread().getName() + " push task " + task);
    }

    @Override
    public synchronized void falied(Task task) throws InterruptedException {
        if (task.getRetry() > 0) {
            this.faildQueue.put(task);
            logger.info(Thread.currentThread().getName() + " push failed task " + task);
        }
    }

    @Override
    public void pushAll(List<Task> tasks) throws InterruptedException {
        for (Task task : tasks) {
            this.push(task);
        }
    }

    @Override
    public void push(List<String> urls) {
        urls.stream().map(url -> new Task(url)).forEach(task -> {
            try {
                this.push(task);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        });
    }

    @Override
    public void clear(){
        this.queue.clear();
        logger.info(Thread.currentThread().getName() + " clear queue");
    }

    @Override
    public CockroachQueue filter(IQueueTaskFilter filter) throws Exception {
        this.filterBox.add(filter);
        return this;
    }
}
