package com._520.crowdfunding.service;

import com._520.crowdfunding.domain.TAdmin;

import java.util.Map;

public interface TAdminService {
    TAdmin checkLogin(Map<String, Object> map);
}
