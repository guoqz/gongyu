package com.guoqz.gongyu.service;

import com.guoqz.gongyu.bean.Fix;
import com.guoqz.gongyu.util.Result;

public interface FixService {

    public Result getFixList(int page, int limit);

    public Result addFix(Fix fix);

    public Fix getFixById(int id);

    public Result updateFix(Fix fix);

    public Result deleteFixById(int id);

}
