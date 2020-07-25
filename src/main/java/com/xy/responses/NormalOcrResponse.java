package com.xy.responses;

import com.alibaba.fastjson.annotation.JSONField;

import java.util.Arrays;

public class NormalOcrResponse {
    @JSONField(name = "log_id")
    private long logId;
    @JSONField(name = "words_result_num")
    private int wordsResultNum;
    @JSONField(name = "words_result")
    private Words[] wordResult;

    public String wordsLines() {
        StringBuilder stringBuilder = new StringBuilder();
        for (Words words : wordResult) {
            stringBuilder.append(words);
            stringBuilder.append("\n");
        }
        return stringBuilder.toString();
    }

    @Override
    public String toString() {
        return "BasicOcrResponse{" +
                "logId=" + logId +
                ", wordsResultNum=" + wordsResultNum +
                ", wordResult=" + Arrays.toString(wordResult) +
                '}';
    }

    public long getLogId() {
        return logId;
    }

    public void setLogId(long logId) {
        this.logId = logId;
    }

    public int getWordsResultNum() {
        return wordsResultNum;
    }

    public void setWordsResultNum(int wordsResultNum) {
        this.wordsResultNum = wordsResultNum;
    }

    public Words[] getWordResult() {
        return wordResult;
    }

    public void setWordResult(Words[] wordResult) {
        this.wordResult = wordResult;
    }

    public static class Words {
        @JSONField(name = "words")
        private String words;
        @JSONField(name = "probability")
        private Probability probability;

        @Override
        public String toString() {
            return "Words{" +
                    "words='" + words + '\'' +
                    ", probability=" + probability +
                    '}';
        }

        public String getWords() {
            return words;
        }

        public void setWords(String words) {
            this.words = words;
        }

        public Probability getProbability() {
            return probability;
        }

        public void setProbability(Probability probability) {
            this.probability = probability;
        }
    }

    public static class Probability {
        @JSONField(name = "variance")
        private double variance;
        @JSONField(name = "average")
        private double average;
        @JSONField(name = "min")
        private double min;

        @Override
        public String toString() {
            return "Probability{" +
                    "variance=" + variance +
                    ", average=" + average +
                    ", min=" + min +
                    '}';
        }

        public double getVariance() {
            return variance;
        }

        public void setVariance(double variance) {
            this.variance = variance;
        }

        public double getAverage() {
            return average;
        }

        public void setAverage(double average) {
            this.average = average;
        }

        public double getMin() {
            return min;
        }

        public void setMin(double min) {
            this.min = min;
        }
    }
}
