package com.xy.responses;

import com.alibaba.fastjson.annotation.JSONField;

public class TableOcrResponse {
    @JSONField(name = "log_id")
    private long logId;
    @JSONField(name = "result")
    private Result result;

    public static class Result{
        @JSONField(name = "result_data")
        private String resultData;
        @JSONField(name = "ret_msg")
        private String retMsg;
        @JSONField(name = "percent")
        private int percent;
        @JSONField(name = "ret_code")
        private int retCode;

        public String getResultData() {
            return resultData;
        }

        public void setResultData(String resultData) {
            this.resultData = resultData;
        }

        public int getPercent() {
            return percent;
        }

        public void setPercent(int percent) {
            this.percent = percent;
        }

        public int getRetCode() {
            return retCode;
        }

        public void setRetCode(int retCode) {
            this.retCode = retCode;
        }

        public String getRetMsg() {
            return retMsg;
        }

        public void setRetMsg(String retMsg) {
            this.retMsg = retMsg;
        }

        @Override
        public String toString() {
            return "Result{" +
                    "resultDate='" + resultData + '\'' +
                    ", retMag='" + retMsg + '\'' +
                    ", percent=" + percent +
                    ", retCode=" + retCode +
                    '}';
        }
    }

    public long getLogId() {
        return logId;
    }

    public void setLogId(long logId) {
        this.logId = logId;
    }

    public Result getResult() {
        return result;
    }

    public void setResult(Result result) {
        this.result = result;
    }

    @Override
    public String toString() {
        return "TableOcrResponse{" +
                "logId=" + logId +
                ", result=" + result +
                '}';
    }
}
