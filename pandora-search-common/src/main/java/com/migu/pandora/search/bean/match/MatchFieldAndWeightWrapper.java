package com.migu.pandora.search.bean.match;

/**
 * Created by piguangtao on 25/1/18.
 */
public class MatchFieldAndWeightWrapper {
    /**
     * 不拆词域
     */
    private String fieldWithParticiple;

    /**
     * 拆词域
     */
    private String fieldWithoutParticiple;

    /**
     * 全拼域
     */
    private String fieldQuanPin;

    /**
     * 简拼域
     */
    private String fieldJianPin;

    /**
     * 精确匹配权重
     */
    private float weightForAccurateMatch = 1f;
    /**
     * 模糊匹配权重
     */
    private float weightForDimMatch = 1f;

    /**
     * 纠错词匹配权重
     */
    private float weightForSpellCheckWord = 1f;

    /**
     * 前缀匹配权重
     */
    private float weightForPrefixMatch = 1f;


    public String getFieldWithParticiple() {
        return fieldWithParticiple;
    }

    public void setFieldWithParticiple(String fieldWithParticiple) {
        this.fieldWithParticiple = fieldWithParticiple;
    }

    public String getFieldWithoutParticiple() {
        return fieldWithoutParticiple;
    }

    public void setFieldWithoutParticiple(String fieldWithoutParticiple) {
        this.fieldWithoutParticiple = fieldWithoutParticiple;
    }

    public String getFieldQuanPin() {
        return fieldQuanPin;
    }

    public void setFieldQuanPin(String fieldQuanPin) {
        this.fieldQuanPin = fieldQuanPin;
    }

    public String getFieldJianPin() {
        return fieldJianPin;
    }

    public void setFieldJianPin(String fieldJianPin) {
        this.fieldJianPin = fieldJianPin;
    }

    public float getWeightForAccurateMatch() {
        return weightForAccurateMatch;
    }

    public void setWeightForAccurateMatch(float weightForAccurateMatch) {
        this.weightForAccurateMatch = weightForAccurateMatch;
    }

    public float getWeightForDimMatch() {
        return weightForDimMatch;
    }

    public void setWeightForDimMatch(float weightForDimMatch) {
        this.weightForDimMatch = weightForDimMatch;
    }

    public float getWeightForSpellCheckWord() {
        return weightForSpellCheckWord;
    }

    public void setWeightForSpellCheckWord(float weightForSpellCheckWord) {
        this.weightForSpellCheckWord = weightForSpellCheckWord;
    }

    public float getWeightForPrefixMatch() {
        return weightForPrefixMatch;
    }

    public void setWeightForPrefixMatch(float weightForPrefixMatch) {
        this.weightForPrefixMatch = weightForPrefixMatch;
    }

    @Override
    public String toString() {
        return "MatchFieldAndWeightWrapper{" +
                "fieldWithParticiple='" + fieldWithParticiple + '\'' +
                ", fieldWithoutParticiple='" + fieldWithoutParticiple + '\'' +
                ", fieldQuanPin='" + fieldQuanPin + '\'' +
                ", fieldJianPin='" + fieldJianPin + '\'' +
                ", weightForAccurateMatch=" + weightForAccurateMatch +
                ", weightForDimMatch=" + weightForDimMatch +
                ", weightForSpellCheckWord=" + weightForSpellCheckWord +
                ", weightForPrefixMatch=" + weightForPrefixMatch +
                '}';
    }

    public MatchFieldAndWeightWrapper(String fieldWithParticiple, String fieldWithoutParticiple, String fieldQuanPin,
                                      String fieldJianPin, float weightForAccurateMatch, float weightForDimMatch,
                                      float weightForSpellCheckWord, float weightForPrefixMatch) {
        this.fieldWithParticiple = fieldWithParticiple;
        this.fieldWithoutParticiple = fieldWithoutParticiple;
        this.fieldQuanPin = fieldQuanPin;
        this.fieldJianPin = fieldJianPin;
        this.weightForAccurateMatch = weightForAccurateMatch;
        this.weightForDimMatch = weightForDimMatch;
        this.weightForSpellCheckWord = weightForSpellCheckWord;
        this.weightForPrefixMatch = weightForPrefixMatch;
    }

    public static Builder builder() {
        return new Builder();
    }

    public static class Builder {
        private String fieldWithParticiple;
        private String fieldWithoutParticiple;
        private String fieldQuanPin;
        private String fieldJianPin;
        private float weightForAccurateMatch;
        private float weightForDimMatch;
        private float weightForSpellCheckWord;
        private float weightForPrefixMatch;

        public Builder setFieldWithParticiple(final String fieldWithParticiple) {
            this.fieldWithParticiple = fieldWithParticiple;
            return this;
        }

        public Builder setFieldWithoutParticiple(final String fieldWithoutParticiple) {
            this.fieldWithoutParticiple = fieldWithoutParticiple;
            return this;
        }

        public Builder setFieldQuanPin(final String fieldQuanPin) {
            this.fieldQuanPin = fieldQuanPin;
            return this;
        }

        public Builder setFieldJianPin(final String fieldJianPin) {
            this.fieldJianPin = fieldJianPin;
            return this;
        }

        public Builder setWeightForAccurateMatch(final float weightForAccurateMatch) {
            this.weightForAccurateMatch = weightForAccurateMatch;
            return this;
        }

        public Builder setWeightForDimMatch(final float weightForDimMatch) {
            this.weightForDimMatch = weightForDimMatch;
            return this;
        }

        public Builder setWeightForSpellCheckWord(final float weightForSpellCheckWord) {
            this.weightForSpellCheckWord = weightForSpellCheckWord;
            return this;
        }

        public Builder setWeightForPrefixMatch(final float weightForPrefixMatch) {
            this.weightForPrefixMatch = weightForPrefixMatch;
            return this;
        }

        public MatchFieldAndWeightWrapper build() {
            return new MatchFieldAndWeightWrapper(this.fieldWithParticiple, this.fieldWithoutParticiple, this
                    .fieldQuanPin, this.fieldJianPin, this.weightForAccurateMatch, this.weightForDimMatch, this
                    .weightForSpellCheckWord, this.weightForPrefixMatch);
        }
    }
}
