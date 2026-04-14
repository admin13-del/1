package com.migu.pandora.search.bean.spellcheck;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by piguangtao on 29/1/18.
 */
public class SpellResp {
    public static final String SUCCESS = "200";
    private String code = SUCCESS;
    private String message = "操作成功";
    private long costTime;
    
    
    private List<SpellRespResult> results = new ArrayList<>();

    private List<SpellCheckItem> datas = new ArrayList<>();

    public List<SpellCheckItem> getDatas() {
        return datas;
    }

    public void setDatas(List<SpellCheckItem> datas) {
        this.datas = datas;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public long getCostTime() {
        return costTime;
    }

    public void setCostTime(long costTime) {
        this.costTime = costTime;
    }

    public List<SpellRespResult> getResults() {
        return results;
    }

    public void setResults(List<SpellRespResult> results) {
        this.results = results;
    }
    
    public SpellResp() {
    }

    public SpellResp(String code, String message, long costTime, List<SpellRespResult> results) {
        this.code = code;
        this.message = message;
        this.costTime = costTime;
        this.results = results;
    }

    public static Builder builder() {
        return new Builder();
    }

    public static class Builder {
        private String code;
        private String message;
        private long costTime;
        private List<SpellRespResult> results;

        public Builder setCode(final String code) {
            this.code = code;
            return this;
        }

        public Builder setMessage(final String message) {
            this.message = message;
            return this;
        }

        public Builder setCostTime(final long costTime) {
            this.costTime = costTime;
            return this;
        }

        public Builder setResults(final List<SpellRespResult> results) {
            this.results = results;
            return this;
        }

        public SpellResp build() {
            return new SpellResp(this.code, this.message, this.costTime, this.results);
        }
    }

    public static class SemanticSpellResult extends SpellRespResult {
        /**
         * 语义匹配规则的优先级
         */
        private Integer priority;

        /**
         * 是否继续纠错
         */
        private Integer continueSpellCheck;


        public SemanticSpellResult(String name, String type) {
            super(name, type);
        }

        public Integer getPriority() {
            return priority;
        }

        public void setPriority(Integer priority) {
            this.priority = priority;
        }

        public Integer getContinueSpellCheck() {
            return continueSpellCheck;
        }

        public void setContinueSpellCheck(Integer continueSpellCheck) {
            this.continueSpellCheck = continueSpellCheck;
        }

        @Override
        public String toString() {
            return "SemanticSpellResult{" +
                    "priority=" + priority +
                    ", continueSpellCheck=" + continueSpellCheck +
                    "} " + super.toString();
        }
    }

    public static class SpellRespResult {
        /**
         * 纠错后的词
         */
        private String name;

        /**
         * 纠错的词所属类型
         */
        private SpellCheckFieldType type = SpellCheckFieldType.NONE;

        //默认得分为100分
        /**
         * 纠错后词得分
         */
        private double score = 100;

        private float hot;
        
        private int rel;

        private int opHot;

		private String subType;
		/**
		 * @return 返回 subType
		 */
		public String getSubType() {
			return subType;
		}
		/**
		 * @param 对subType进行赋值
		 */
		public void setSubType(String subType) {
			this.subType = subType;
		}
		public SpellRespResult() {
        }

        public int getRel() {
            return rel;
        }

        public void setRel(int rel) {
            this.rel = rel;
        }

        public SpellRespResult(String name, String type) {
            this.name = name;
            this.type = SpellCheckFieldType.get(type);
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public SpellCheckFieldType getType() {
            return type;
        }

        public void setType(SpellCheckFieldType type) {
            this.type = type;
        }

        public double getScore() {
            return score;
        }

        public void setScore(double score) {
            this.score = score;
        }

        public float getHot() {
            return hot;
        }

        public void setHot(float hot) {
            this.hot = hot;
        }

        public int getOpHot() {
            return opHot;
        }

        public void setOpHot(int opHot) {
            this.opHot = opHot;
        }

        @Override
        public String toString() {
            return "SpellRespResult [name=" + name + ", type=" + type + ", score=" + score + ", hot=" + hot + ", rel="
                    + rel + "]";
        }
       
    }

    public static List<SpellRespResult> getResultsByType(SpellResp spellResp, SpellCheckFieldType... fieldTypes) {
        if (null == spellResp) {
            return null;
        }
        if (null == fieldTypes) {
            return spellResp.getResults();
        }

        List<SpellRespResult> result = new ArrayList<>();

        for (SpellRespResult single : spellResp.getResults()) {
            for (SpellCheckFieldType spellCheckFieldType : fieldTypes) {
                //没有纠错类型,则默认为吻合
                if (SpellCheckFieldType.NONE.getCode().equalsIgnoreCase(spellCheckFieldType.getCode()) ||
                        spellCheckFieldType
                                .getCode().equalsIgnoreCase(single
                                .getType().getCode())) {
                    result.add(single);
                    break;
                }
            }

        }

        return result;
    }


    @Override
    public String toString() {
        return "SpellResp{" +
                "code='" + code + '\'' +
                ", message='" + message + '\'' +
                ", costTime=" + costTime +
                ", results=" + results +
                '}';
    }
}
