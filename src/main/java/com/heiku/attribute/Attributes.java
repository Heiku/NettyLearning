package com.heiku.attribute;

import com.heiku.session.Session;
import io.netty.util.AttributeKey;

public interface Attributes {

    AttributeKey<Session> SESSION = AttributeKey.newInstance("sesion");
}
