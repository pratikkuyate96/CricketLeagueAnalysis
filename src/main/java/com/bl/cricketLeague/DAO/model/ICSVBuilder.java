package com.bl.cricketLeague.DAO.model;

import com.bl.cricketLeague.DAO.exception.CricketAnalyserException;

import java.io.Reader;
import java.util.Iterator;
import java.util.List;

public interface ICSVBuilder<E> {
    Iterator<E> getCSVFileIterator(Reader reader, Class csvClass) throws CricketAnalyserException;
    List<E> getCSVFileList(Reader reader, Class csvClass) throws CricketAnalyserException;

}