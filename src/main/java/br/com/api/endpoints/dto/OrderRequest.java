package br.com.api.endpoints.dto;

import br.com.api.entity.Status;

public class OrderRequest {
    private Status status;

    public Status getStatus() {
        return status;
    }

    public void setStatus(Status status) {
        this.status = status;
    }
}
