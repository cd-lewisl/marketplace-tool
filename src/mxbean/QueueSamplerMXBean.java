package mxbean;

public interface QueueSamplerMXBean {
    public String getK();
    public QueueSample getQueueSample(); 
    public void clearQueue(); 
}
