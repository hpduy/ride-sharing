 package com.bikiegang.ridesharing.annn.framework.util;
 
 import java.util.ArrayList;
 import java.util.List;
 
 public class NCTUtils
 {
   public static List<String> pagingListString(List<String> list, int offset, int count)
   {
     List result = new ArrayList();
     if (list != null) {
       int lsCount = list.size();
       if (offset > lsCount) {
         return result;
       }
       int fromIndex = offset < 0 ? 0 : offset;
       int toIndex = offset + count;
       toIndex = toIndex > lsCount ? lsCount : toIndex;
       result = list.subList(fromIndex, toIndex);
     }
     return result;
   }
 
   public static List<Long> pagingListLong(List<Long> list, int offset, int count) {
     List result = new ArrayList();
     if (list != null) {
       int lsCount = list.size();
       if (offset > lsCount) {
         return result;
       }
       int fromIndex = offset < 0 ? 0 : offset;
       int toIndex = offset + count;
       toIndex = toIndex > lsCount ? lsCount : toIndex;
       result = list.subList(fromIndex, toIndex);
     }
     return result;
   }
 }

/* 
 * Qualified Name:     com.annn.framework.util.NCTUtils
 * 
 */