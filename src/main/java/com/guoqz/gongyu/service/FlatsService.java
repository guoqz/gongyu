package com.guoqz.gongyu.service;

import com.guoqz.gongyu.bean.Flats;
import com.guoqz.gongyu.util.Result;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

public interface FlatsService {

    public List<Flats> getAllFlats();

    public Result getFlatsList(int page,int limit);

    public Result isFlatsExists(String name);

    public Result addFlats(Flats flats);

    public Flats getFlatsById(int id);

    public Result updateFlats(Flats flats);

    public Result deleteFlatsById(int id);

}
