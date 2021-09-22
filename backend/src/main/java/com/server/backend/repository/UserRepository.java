package com.server.backend.Repository;

import com.server.backend.models.User;

public interface UserRepository{

  User findUserById(String Username);

}
