package atc.gui.admin.domain;

public interface RecordHolder
{
    public Long getCallId();

    public default String getRecordSource() {
        // return "https://www.freesound.org/data/previews/381/381894_2788693-lq.mp3?callId=";
        return String.format("/static/record/%d", getCallId());
    }
}
