package com.zhangyingwei.cockroach.executer.task;

import java.util.Comparator;

/**
 * Created by zhangyw on 2018/1/23.
 * 决定 task 出队的优先级
 */
public class TaskCompatator implements Comparator<Task> {
    @Override
    public int compare(Task task1, Task task2) {
        return task1.compareTo(task2);
    }
}
