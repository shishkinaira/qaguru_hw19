package in.reqres.models;

import lombok.Data;

@Data
public class CreateUserResponseModel {

    String name, job;
    Integer id;
    String createdAt;
}
