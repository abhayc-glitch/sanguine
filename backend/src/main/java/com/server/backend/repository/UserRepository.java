package com.server.backend.repository;

import com.server.backend.models.User;

public interface UserRepository{

  User findUserById(String Username);

}
