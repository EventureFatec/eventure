package com.github.eventure.storage;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Predicate;
import java.util.stream.Stream;

public class Storage<T> extends ArrayList<T> {
    public Stream<T> find(Predicate<? super T> p) {
        return this.parallelStream().filter(p);
    }
    
    public List<T> toList() {
        return new ArrayList<>(this); // Cria uma nova lista com os elementos atuais
    }
}
