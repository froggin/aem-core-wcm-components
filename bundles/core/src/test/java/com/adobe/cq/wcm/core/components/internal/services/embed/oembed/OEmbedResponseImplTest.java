/*~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
 ~ Copyright 2019 Adobe Systems Incorporated
 ~
 ~ Licensed under the Apache License, Version 2.0 (the "License");
 ~ you may not use this file except in compliance with the License.
 ~ You may obtain a copy of the License at
 ~
 ~     http://www.apache.org/licenses/LICENSE-2.0
 ~
 ~ Unless required by applicable law or agreed to in writing, software
 ~ distributed under the License is distributed on an "AS IS" BASIS,
 ~ WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 ~ See the License for the specific language governing permissions and
 ~ limitations under the License.
 ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~*/
package com.adobe.cq.wcm.core.components.internal.services.embed.oembed;

import java.io.IOException;
import java.io.InputStream;

import static org.junit.Assert.*;
import org.junit.Test;

import com.adobe.cq.wcm.core.components.models.embed.oembed.OEmbedResponse;
import com.fasterxml.jackson.databind.ObjectMapper;

public class OEmbedResponseImplTest {

    @Test
    public void testFromJSONFile() throws IOException {
        OEmbedResponse response = getResponseFromJSONFile("embed/oembed/response.json");
        assertNotNull(response);
        assertEquals("1.0", response.getVersion());
        assertEquals(OEmbedResponse.Type.VIDEO, OEmbedResponse.Type.fromString(response.getType()));
        assertEquals("YouTube", response.getProviderName());
        assertEquals("http://youtube.com/", response.getProviderUrl());
        assertEquals("425", response.getWidth());
        assertEquals("344", response.getHeight());
        assertEquals("Amazing Nintendo Facts", response.getTitle());
        assertEquals("ZackScott", response.getAuthorName());
        assertEquals("http://www.youtube.com/user/ZackScott", response.getAuthorUrl());
        assertEquals("<object width=\"425\" height=\"344\"><param name=\"movie\" value=\"http://www.youtube.com/v/M3r2XDceM6A&fs=1\"></param><param name=\"allowFullScreen\" value=\"true\"></param><param name=\"allowscriptaccess\" value=\"always\"></param><embed src=\"http://www.youtube.com/v/M3r2XDceM6A&fs=1\"type=\"application/x-shockwave-flash\" width=\"425\" height=\"344\"allowscriptaccess=\"always\" allowfullscreen=\"true\"></embed></object>", response.getHtml());
        assertNull(response.getCacheAge());
        assertNull(response.getThumbnailUrl());
        assertNull(response.getThumbnailWidth());
        assertNull(response.getThumbnailHeight());
        assertNull(response.getUrl());
    }

    protected OEmbedResponse getResponseFromJSONFile(String file) throws IOException {
        ClassLoader classloader = Thread.currentThread().getContextClassLoader();
        InputStream is = classloader.getResourceAsStream(file);
        return new ObjectMapper().readValue(is, OEmbedResponseImpl.class);
    }
}
