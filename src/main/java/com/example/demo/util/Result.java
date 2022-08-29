/*
 * Copyright 1999-2018 Alibaba Group Holding Ltd.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.example.demo.util;

/**
 * @author 顾鹏飞
 * @date 2021/07/22
 * @description 自定义返回体
 */
public class Result<R> {


    /**
     * 接口返回的结果信息，如查询的列表数据、查询的数据详情等
     */
    private R result;

    /**
     * 接口返回的信息，如增删改查成功或失败时返回的信息
     */
    private String message;

    /**
     *
     * 错误代码
     */
    private int error;

    /**
     * 接口调用是否成功
     */
    private Boolean success;


    public static <R> Result<R> ofSuccess(R result) {
        return new Result<R>()
                .setSuccess(true)
                .setResult(result);
    }

    public static <R> Result<R> ofSuccess(R result, String message) {
        return new Result<R>()
                .setSuccess(true)
                .setResult(result)
                .setMessage(message);
    }

    public static <R> Result<R> ofSuccessMsg(String message) {
        return new Result<R>()
                .setSuccess(true)
                .setMessage(message);
    }

    public static <R> Result<R> ofFail(int error, String message) {
        Result<R> result = new Result<>();
        result.setSuccess(false);
        result.setError(error);
        result.setMessage(message);
        return result;
    }

    public static <R> Result<R> ofThrowable(int error, Throwable throwable) {
        Result<R> result = new Result<>();
        result.setSuccess(false);
        result.setError(error);
        result.setMessage(throwable.getClass().getName() + ", " + throwable.getMessage());
        return result;
    }


    public boolean isSuccess() {
        return success;
    }

    public Result<R> setSuccess(boolean success) {
        this.success = success;
        return this;
    }

    public String getMessage() {
        return message;
    }

    public Result<R> setMessage(String message) {
        this.message = message;
        return this;
    }

    public int getError() {
        return error;
    }

    public Result<R> setError(int error) {
        this.error = error;
        return this;
    }

    public R getResult() {
        return result;
    }

    public Result<R> setResult(R result) {
        this.result = result;
        return this;
    }

    @Override
    public String toString() {
        return "Result{" +
                "result=" + result +
                ", message='" + message + '\'' +
                ", error='" + error + '\'' +
                ", success=" + success +
                '}';
    }
}
