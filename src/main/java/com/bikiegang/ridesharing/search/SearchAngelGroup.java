/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.bikiegang.ridesharing.search;

import com.bikiegang.ridesharing.annn.framework.util.ConvertUtils;
import com.bikiegang.ridesharing.annn.framework.util.StringUtils;
import com.bikiegang.ridesharing.database.Database;
import com.bikiegang.ridesharing.pojo.AngelGroup;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 *
 * @author root
 */
public class SearchAngelGroup {

    private SearchAngelGroup() {
    }

    public static SearchAngelGroup getInstance() {
        return SearchAngelGroupHolder.INSTANCE;
    }

    private static class SearchAngelGroupHolder {

        private static final SearchAngelGroup INSTANCE = new SearchAngelGroup();
    }

    public List<Long> Search(String pattern) {
        List<Long> result = new ArrayList<>();

        HashMap<Long, AngelGroup> angelGroupHashMap = Database.getInstance().getAngelGroupHashMap();

        for (Map.Entry<Long, AngelGroup> entrySet : angelGroupHashMap.entrySet()) {
            AngelGroup value = entrySet.getValue();
            boolean Search = StringUtils.Search(StringUtils.join(value.getTagName().toArray(), ","), pattern);
            if (Search) {
                result.add(value.getId());
            }
        }

        return result;
    }
}
