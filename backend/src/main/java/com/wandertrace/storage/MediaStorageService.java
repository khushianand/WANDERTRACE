package com.wandertrace.storage;
import java.io.*;public interface MediaStorageService {StoredObject upload(String key,InputStream content,long size,String contentType)throws IOException;void delete(String key)throws IOException;boolean exists(String key);String getUrl(String key);record StoredObject(String key,String url){} }
