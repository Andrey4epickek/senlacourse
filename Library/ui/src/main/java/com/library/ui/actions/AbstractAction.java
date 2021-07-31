package com.library.ui.actions;


import org.modelmapper.ModelMapper;

import java.io.BufferedReader;
import java.io.InputStreamReader;

public abstract class AbstractAction {
    protected BufferedReader bufferedReader =new BufferedReader(new InputStreamReader(System.in));
    protected ModelMapper mapper=new ModelMapper();
}
