package com.github.rinfield.java8.stream.ops;

import com.github.rinfield.java8.stream.ops.ext.ExtOpenIntrOp;
import com.github.rinfield.java8.stream.ops.ext.ExtShortIntrOp;
import com.github.rinfield.java8.stream.ops.ext.ExtShortTermOp;
import com.github.rinfield.java8.stream.ops.std.StdOpenIntrOp;
import com.github.rinfield.java8.stream.ops.std.StdShortIntrOp;
import com.github.rinfield.java8.stream.ops.std.StdShortTermOp;

public interface CommonStreamOp<T, S extends StreamWrapper<T, S>>
    extends StdOpenIntrOp<T, S>, StdShortIntrOp<T, S>, StdShortTermOp<T, S>,
    ExtOpenIntrOp<T, S>, ExtShortIntrOp<T, S>, ExtShortTermOp<T, S> {
}
