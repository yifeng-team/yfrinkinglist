package com.yfdyf.recommend.qywechat.model;

public class TextcardMessage extends BaseMessage {
    //文本
    private TextCard textcard;

    //btntxt    否    按钮文字。 默认为“详情”， 不超过4个文字，超过自动截断。

    public TextCard getTextcard() {
        return textcard;
    }

    public void setTextcard(TextCard textcard) {
        this.textcard = textcard;
    }



}
