package com.info.info_v2_backend.notice.domain.interview

enum class InterviewProcess(
    val meaning: String,
) {


    DOCUMENT("서류전형"),
    PERSONALITY_TEST("인적성 테스트"),
    CODING_TEST("코딩 테스트"),
    LIVE_CODING("라이브 코딩"),
    AI_INTERVIEW("AI 면접"),
    ASSIGNMENT_REPORT("과제 제출"),
    TECHNOLOGY_INTERVIEW("기술 면접"),
    CULTURE_INTERVIEW("컬쳐 면접"),
    DIRECTOR_INTERVIEW("임원 면접"),
    FINAL_INTERVIEW("최종 면접"),
    PHYSICAL_TEST("신체 검사")

}