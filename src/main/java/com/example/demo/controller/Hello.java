package com.example.demo.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

@Controller
public class Hello {
    @RequestMapping("/")
    public String hello(){
        return "hello";
    }

    @RequestMapping("index")
    public  String index(){
        return "index1";
    }

    public static void main(String[] args) {
//        $("#baseDiv").append("<div id = \"e\">\n" +
//                "                    <div>\n" +
//                "                        <label>Page&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</label>\n" +
//                "                        <input ms-duplex=\"@url\"    type=\"url\" class=\"form-control\"  style=\"width: 455px\"/>\n" +
//                "                    </div>\n" +
//                "                    <div>\n" +
//                "                        <label>saleXpath&nbsp;</label>\n" +
//                "                        <input ms-duplex=\"@saleXpath\"    type=\"url\" class=\"form-control\"  style=\"width: 750px\"/>\n" +
//                "                    </div>\n" +
//                "                    <div>\n" +
//                "                        <label>listXpath&nbsp;&nbsp;</label>\n" +
//                "                        <input ms-duplex=\"@listXpath\"    type=\"url\" class=\"form-control\"  style=\"width: 750px\"/>\n" +
//                "                        <button type=\"button\" class=\"btn btn-danger\" style=\"margin: 10px;\" ms-click=\"@removeInput\">-</button>\n" +
//                "                    </div>\n" +
//                "                </div>")

//        $("#baseDiv").append("<div  id=\"baseDiv\">\n" +
//                "                        <div>\n" +
//                "                            <label>Page&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</label>\n" +
//                "                            <input ms-duplex=\"@url\"    type=\"url\" class=\"form-control\"  style=\"width: 455px\"/>\n" +
//                "                            <button type=\"button\" class=\"btn btn-primary\" style=\"margin: 10px;\" ms-click=\"@addInput\">+</button>\n" +
//                "                            <button type=\"button\" class=\"btn btn-danger\" style=\"margin: 10px;\" ms-click=\"@removeInput\">X</button>\n" +
//                "                            <button type=\"button\" class=\"btn btn-success\" style=\"margin: 1px;\" ms-click=\"@result\">{{@resultButton}}</button>\n" +
//                "                        </div>\n" +
//                "                        <div>\n" +
//                "                            <label>saleXpath&nbsp;</label>\n" +
//                "                            <input ms-duplex=\"@saleXpath\"    type=\"url\" class=\"form-control\"  style=\"width: 750px\"/>\n" +
//                "                        </div>\n" +
//                "                        <div>\n" +
//                "                            <label>listXpath&nbsp;&nbsp;</label>\n" +
//                "                            <input ms-duplex=\"@listXpath\"    type=\"url\" class=\"form-control\"  style=\"width: 750px\"/>\n" +
//                "                        </div>\n" +
//                "                    </div>")




//                <div id = "e">
//                    <div>
//                        <label>Page&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</label>
//                        <input ms-duplex="@url"    type="url" class="form-control"  style="width: 455px"/>
//                    </div>
//                    <div>
//                        <label>saleXpath&nbsp;</label>
//                        <input ms-duplex="@saleXpath"    type="url" class="form-control"  style="width: 750px"/>
//                    </div>
//                    <div>
//                        <label>listXpath&nbsp;&nbsp;</label>
//                        <input ms-duplex="@listXpath"    type="url" class="form-control"  style="width: 750px"/>
//                        <button type="button" class="btn btn-danger" style="margin: 10px;" ms-click="@removeInput">-</button>
//                    </div>
//                </div>

//                        <div id = "e">
//                            <div>
//                                <input ms-duplex="@url"    type="url" class="form-control"  style="width: 455px"/>
//                            </div>
//                            <div>
//                                <input ms-duplex="@saleXpath"    type="url" class="form-control"  style="width: 750px"/>
//                            </div>
//                            <div>
//                                <input ms-duplex="@listXpath"    type="url" class="form-control"  style="width: 750px"/>
//                            </div>
//                        </div>
    }

}
