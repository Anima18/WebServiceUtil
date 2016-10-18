package com.example.webserviceutil.entity;

import java.util.ArrayList;
import java.util.List;

/**
 * 文件上传进度封装类
 * @author 简建鸿 2016/6/12
 * @version 1.0
 */
public class ProgressValue<T> {
    /**
     * 文件名称
     */
    private String fileName;
    /**
     * 文件上载进度
     */
    private int progress;
    /**
     * 上载成功后返回的结果
     */
    private List<T> object = new ArrayList<>();

    public ProgressValue(String fileName, int progress) {
        this.fileName = fileName;
        this.progress = progress;
    }

    public ProgressValue(T t) {
        this.object.add(t);
    }

    public ProgressValue(List<T> tList) {
        this.object = tList;
    }

    public String getFileName() {
        return fileName;
    }

    public void setFileName(String fileName) {
        this.fileName = fileName;
    }

    public int getProgress() {
        return progress;
    }

    public void setProgress(int progress) {
        this.progress = progress;
    }

    public List<T> getObject() {
        return object;
    }

    public void setObject(List<T> object) {
        this.object = object;
    }

   /* @Override
    public int hashCode() {
        return new HashCodeBuilder().append(progress).toHashCode();
    }

    @Override
    public boolean equals(Object obj) {
        if(obj instanceof ProgressValue){
            final ProgressValue other = (ProgressValue) obj;
            return new EqualsBuilder()
                    .append(progress, other.progress)
                    .isEquals();
        } else{
            return false;
        }
    }*/

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        ProgressValue<?> that = (ProgressValue<?>) o;

        return progress == that.progress;

    }

    @Override
    public int hashCode() {
        return progress;
    }
}