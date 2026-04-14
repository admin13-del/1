package com.migu.pandora.search.bean.match;

/**
 * Created by piguangtao on 25/1/18.
 */
public class InputRectification {
    /**
     * 原始输入串
     */
    private String textOriginal;

    /**
     * 去掉空格的输入串
     */
    private String textNoSpace;

    /**
     * 转义后的输入串
     */
    private String textEscaped;

    /**
     * 过滤特殊字符后的输入串
     */
    private String textFiltered;

    /**
     * 过滤特殊字符后的转义输入串
     */
    private String textFilteredEscape;

    /**
     * 是否过滤
     *
     * @return
     */
    public boolean isFiltered() {
        return !textOriginal.equals(textFiltered);
    }

    public String getTextOriginal() {
        return textOriginal;
    }

    public void setTextOriginal(String textOriginal) {
        this.textOriginal = textOriginal;
    }

    public String getTextNoSpace() {
        return textNoSpace;
    }

    public void setTextNoSpace(String textNoSpace) {
        this.textNoSpace = textNoSpace;
    }

    public String getTextEscaped() {
        return textEscaped;
    }

    public void setTextEscaped(String textEscaped) {
        this.textEscaped = textEscaped;
    }

    public String getTextFiltered() {
        return textFiltered;
    }

    public void setTextFiltered(String textFiltered) {
        this.textFiltered = textFiltered;
    }

    public String getTextFilteredEscape() {
        return textFilteredEscape;
    }

    public void setTextFilteredEscape(String textFilteredEscape) {
        this.textFilteredEscape = textFilteredEscape;
    }

    @Override
    public String toString() {
        return "InputRectification{" +
                "textOriginal='" + textOriginal + '\'' +
                ", textNoSpace='" + textNoSpace + '\'' +
                ", textEscaped='" + textEscaped + '\'' +
                ", textFiltered='" + textFiltered + '\'' +
                ", textFilteredEscape='" + textFilteredEscape + '\'' +
                '}';
    }

    public InputRectification(String textOriginal, String textNoSpace, String textEscaped, String textFiltered, String textFilteredEscape) {
        this.textOriginal = textOriginal;
        this.textNoSpace = textNoSpace;
        this.textEscaped = textEscaped;
        this.textFiltered = textFiltered;
        this.textFilteredEscape = textFilteredEscape;
    }

    public static Builder builder() {
        return new Builder();
    }

    public static class Builder {
        private String textOriginal;
        private String textNoSpace;
        private String textEscaped;
        private String textFiltered;
        private String textFilteredEscape;

        public Builder setTextOriginal(final String textOriginal) {
            this.textOriginal = textOriginal;
            return this;
        }

        public Builder setTextNoSpace(final String textNoSpace) {
            this.textNoSpace = textNoSpace;
            return this;
        }

        public Builder setTextEscaped(final String textEscaped) {
            this.textEscaped = textEscaped;
            return this;
        }

        public Builder setTextFiltered(final String textFiltered) {
            this.textFiltered = textFiltered;
            return this;
        }

        public Builder setTextFilteredEscape(final String textFilteredEscape) {
            this.textFilteredEscape = textFilteredEscape;
            return this;
        }

        public InputRectification build() {
            return new InputRectification(this.textOriginal, this.textNoSpace, this.textEscaped, this.textFiltered, this.textFilteredEscape);
        }
    }
}
