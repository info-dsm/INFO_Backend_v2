package com.info.info_v2_backend.common.file.type

enum class DocsExt(
    val extension: String,
    val contentType: String
) {
    DOC("doc", "application/msword"),
    DOCX("docx", "application/msword"),
    HWP("hwp", "application/msword"),
    TXT("txt", "text/plain"),
    MD("md", "text/plain"),
    PDF("pdf", "application/pdf"),
    XLS("xls", "application/x-msexcel"),
    PPT("ppt", "application/mspowerpoint"),
    PPTX("pptx", "application/mspowerpoint"),
    KEY("key", "application/octet-stream"),
    SHOW("show", "application/octet-stream")

}