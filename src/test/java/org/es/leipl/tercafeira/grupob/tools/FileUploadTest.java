package org.es.leipl.tercafeira.grupob.tools;

import org.junit.jupiter.api.Test;

import javax.swing.*;

import static org.junit.jupiter.api.Assertions.*;

class FileUploadTest {

    @Test
    void uploadLocal() {
        JFrame frame = new JFrame();
        FileUpload fu = new FileUpload(frame);
        fu.uploadLocal();
        assertEquals(26003, fu.getHorario().getAulasList().size());
        assertNotNull(fu.getHorario());
    }

    @Test
    void saveLocal() {
    }

    @Test
    void uploadUrl() {
    }

    @Test
    void getHorario() {
    }
}