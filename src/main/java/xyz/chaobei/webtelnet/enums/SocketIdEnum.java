package xyz.chaobei.webtelnet.enums;

public enum SocketIdEnum {

    TELNET("telnet连接","telnet");

    private final String desc;

    private final String value;

    SocketIdEnum(String desc, String value) {
        this.desc = desc;
        this.value = value;
    }

    public String getDesc() {
        return desc;
    }

    public String getValue() {
        return value;
    }
}
