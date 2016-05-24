package com.zhaoliang.javase8.chapterone;

import java.util.Collection;
import java.util.function.Consumer;
import java.util.function.Predicate;

/**
 * Created by zhaoliang{weston_contribute@163.com} on 2016/5/24.
 */
public interface Collection2<T> extends Collection<T> {
    default void forEachIf(Consumer<T> action, Predicate<T> filter) {
        this.stream().filter(t -> filter.test(t)).forEach(action::accept);
    }
}
