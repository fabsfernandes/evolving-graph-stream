package br.ufu.lsi.gephi.model;

import java.util.Date;

public class Call {
    
    private Date day;
    private Long time;
    private Long duration;
    private String numberA;
    private String numberB;
    private Long contractA;
    private Long contractB;
    private Long startCellId;
    private Long endCellId;
    private Direction direction;
    private CallType callType;
    private CallType destinationType;
    private boolean droppedCall;
    private boolean voiceMail;
    public Date getDay() {
        return day;
    }
    public void setDay( Date day ) {
        this.day = day;
    }
    public Long getTime() {
        return time;
    }
    public void setTime( Long time ) {
        this.time = time;
    }
    public Long getDuration() {
        return duration;
    }
    public void setDuration( Long duration ) {
        this.duration = duration;
    }
    public String getNumberA() {
        return numberA;
    }
    public void setNumberA( String numberA ) {
        this.numberA = numberA;
    }
    public String getNumberB() {
        return numberB;
    }
    public void setNumberB( String numberB ) {
        this.numberB = numberB;
    }
    public Long getContractA() {
        return contractA;
    }
    public void setContractA( Long contractA ) {
        this.contractA = contractA;
    }
    public Long getContractB() {
        return contractB;
    }
    public void setContractB( Long contractB ) {
        this.contractB = contractB;
    }
    public Long getStartCellId() {
        return startCellId;
    }
    public void setStartCellId( Long startCellId ) {
        this.startCellId = startCellId;
    }
    public Long getEndCellId() {
        return endCellId;
    }
    public void setEndCellId( Long endCellId ) {
        this.endCellId = endCellId;
    }
    public Direction getDirection() {
        return direction;
    }
    public void setDirection( Direction direction ) {
        this.direction = direction;
    }
    public CallType getCallType() {
        return callType;
    }
    public void setCallType( CallType callType ) {
        this.callType = callType;
    }
    public CallType getDestinationType() {
        return destinationType;
    }
    public void setDestinationType( CallType destinationType ) {
        this.destinationType = destinationType;
    }
    public boolean isDroppedCall() {
        return droppedCall;
    }
    public void setDroppedCall( boolean droppedCall ) {
        this.droppedCall = droppedCall;
    }
    public boolean isVoiceMail() {
        return voiceMail;
    }
    public void setVoiceMail( boolean voiceMail ) {
        this.voiceMail = voiceMail;
    }
    @Override
    public String toString() {
        return "Call [day=" + day + ", time=" + time + ", duration=" + duration + ", numberA="
                + numberA + ", numberB=" + numberB + ", contractA=" + contractA + ", contractB="
                + contractB + ", startCellId=" + startCellId + ", endCellId=" + endCellId
                + ", direction=" + direction + ", callType=" + callType + ", destinationType="
                + destinationType + ", droppedCall=" + droppedCall + ", voiceMail=" + voiceMail
                + "]";
    }
    
    

}
