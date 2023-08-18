package com.example.eventsourcing.projection;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.persistence.Embeddable;

import java.io.Serializable;

@Embeddable
public class WaypointProjection implements Serializable {

    private String address;
    @JsonProperty("lat")
    private double latitude;
    @JsonProperty("lon")
    private double longitude;

    public WaypointProjection() {
    }

    public String getAddress() {
        return this.address;
    }

    public double getLatitude() {
        return this.latitude;
    }

    public double getLongitude() {
        return this.longitude;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    @JsonProperty("lat")
    public void setLatitude(double latitude) {
        this.latitude = latitude;
    }

    @JsonProperty("lon")
    public void setLongitude(double longitude) {
        this.longitude = longitude;
    }

    public boolean equals(final Object o) {
        if (o == this) return true;
        if (!(o instanceof WaypointProjection)) return false;
        final WaypointProjection other = (WaypointProjection) o;
        if (!other.canEqual(this)) return false;
        final Object thisAddress = this.getAddress();
        final Object otherAddress = other.getAddress();
        if (thisAddress == null ? otherAddress != null : !thisAddress.equals(otherAddress)) return false;
        if (Double.compare(this.getLatitude(), other.getLatitude()) != 0) return false;
        if (Double.compare(this.getLongitude(), other.getLongitude()) != 0) return false;
        return true;
    }

    protected boolean canEqual(final Object other) {
        return other instanceof WaypointProjection;
    }

    public int hashCode() {
        final int PRIME = 59;
        int result = 1;
        final Object thisAddress = this.getAddress();
        result = result * PRIME + (thisAddress == null ? 43 : thisAddress.hashCode());
        final long $latitude = Double.doubleToLongBits(this.getLatitude());
        result = result * PRIME + (int) ($latitude >>> 32 ^ $latitude);
        final long $longitude = Double.doubleToLongBits(this.getLongitude());
        result = result * PRIME + (int) ($longitude >>> 32 ^ $longitude);
        return result;
    }

    public String toString() {
        return "WaypointProjection(address=" + this.getAddress() + ", latitude=" + this.getLatitude() + ", longitude=" + this.getLongitude() + ")";
    }
}
