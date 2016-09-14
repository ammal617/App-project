package com.frsvarsmakten.app;

/**
 * Created by T420S on 2014-04-08.
 */
public class assignment {
    private long DeploymentId;
    private String prio;
    private String DepType;
    private String DepDescript;
    private String Longitud;
    private String Latitud;

    public long getDeploymentId() {
        return DeploymentId;
    }

    public void setDeploymentId(long contactId) {
        this.DeploymentId = DeploymentId;
    }

    public String getPrio() {
        return prio;
    }

    public void setPrio(String prio) {
        this.prio = prio;
    }

    public String getDepType() {
        return DepType;
    }

    public void setDepType(String DepType) {this.DepType = DepType;}

    public String getDepDescript() {
        return DepDescript;
    }

    public void setDepDescript(String DepDescript) {
        this.DepDescript = DepDescript;
    }

    public String getLongitud() {
        return Longitud;
    }

    public void setLongitud(String Longitud) {
        this.Longitud = Longitud;
    }

    public String getLatitud() {
        return Latitud;
    }

    public void setLatitud(String Latitud) {
        this.Latitud = Latitud;
    }


    // Will be used by the ArrayAdapter in the ListView
    @Override
    public String toString() {
        return DepDescript;
    }
}