/*
 * Copyright 2025 The ChaosBlade Authors
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.alibaba.chaosblade.exec.common.model.action.threadpool;

import java.util.concurrent.SynchronousQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

/** @author Changjun Xiao */
public class DefaultThreadPoolFullExecutor extends AbstractThreadPoolFullExecutor {

  public static final String BLADE_MOCK_THREAD_NAME = "ChaosBlade-Mock";

  @Override
  public ThreadPoolExecutor getThreadPoolExecutor() throws Exception {
    return new ThreadPoolExecutor(
        0,
        Integer.MAX_VALUE,
        Integer.MAX_VALUE,
        TimeUnit.SECONDS,
        new SynchronousQueue<Runnable>(),
        new NamedThreadFactory(BLADE_MOCK_THREAD_NAME));
  }
}
