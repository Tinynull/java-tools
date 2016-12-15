package com.zhaoliang.jackson.core;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

/**
 * Created by zhaoliang (weston_contribute@163.com) on 2016/12/14.
 */
// means that if we see "foo" or "bar" in JSON, they will be quietly skipped
// regardless of whether POJO has such properties
@JsonIgnoreProperties({ "foo", "bar" })
public class MyBean
{
    // will not be written as JSON; nor assigned from JSON:
    @JsonIgnore
    public String internal;

    // no annotation, public field is read/written normally
    public String external;

}
