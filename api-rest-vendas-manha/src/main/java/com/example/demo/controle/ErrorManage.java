package com.example.demo.controle;

import org.springframework.http.HttpStatusCode;

public record ErrorManage (String message, HttpStatusCode status ) {

}
