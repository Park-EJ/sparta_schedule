# 일정 관리 앱

## API 명세서
| Aa 기능     | Method | URL                                                                                                                 | Request                                                                                        | Response                                                                                                                                                                                                                                                                                                                                                                                 | StatusCode                                                                                | Description                                                  |
|-----------|--------|---------------------------------------------------------------------------------------------------------------------|------------------------------------------------------------------------------------------------|------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------|-------------------------------------------------------------------------------------------|--------------------------------------------------------------|
| 일정 생성     | POST   | /schedules                                                                                                          | {<br/>"name" : "작성자",<br/>"password" : "비밀번호",<br/>"title" : "제목",<br/>"contents" : "내용"<br/>} | {<br/>"id" : "1",<br/>"name" : "작성자",<br/>"title" : "제목",<br/>"contents" : "내용",<br/>"createdAt" : "2025-02-02T17:11:10.25361",<br/>"updatedAt" : "2025-02-02T17:11:10.25361"<br/>}                                                                                                                                                                                                      | Code : 200                                                                                | 최초 입력 시, 수정일은 작성일과 동일                                        |
| 전체 일정 조회  | GET    | /schedules<br/><br/>/schedules?updatedAt=2025-02-02<br/><br/>/schedules?name=작성자<br/><br/>/schedules?updatedAt=2025-02-02&name=작성자 | -                                                                                              | [<br/>{<br/>"id" : "2",<br/>"name" : "작성자",<br/>"title" : "제목",<br/>"contents" : "내용",<br/>"createdAt" : "2025-02-02T17:11:10.25361",<br/>"updatedAt" : "2025-02-04T17:11:10.25361"<br/>},<br/>{<br/>"id" : "1",<br/>"name" : "작성자",<br/>"title" : "제목",<br/>"contents" : "내용",<br/>"createdAt" : "2025-02-02T17:11:10.25361",<br/>"updatedAt" : "2025-02-02T17:11:10.25361"<br/>}<br/>] | Code : 200<br/><br/>Code : 404<br/>Content : "해당 일정이 존재하지 않습니다."                               | 수정일, 작성자 조건을 바탕으로 등록된 일정 목록을 전부 조회<br/><br/>수정일 기준 내림차순으로 정렬하여 조회 |
| 단건 일정 조회  | GET    | /schedules/{id}                                                                                                     | -                                                                                              | {<br/>"id" : "1",<br/>"name" : "작성자",<br/>"title" : "제목",<br/>"contents" : "내용",<br/>"createdAt" : "2025-02-02T17:11:10.25361",<br/>"updatedAt" : "2025-02-02T17:11:10.25361"<br/>}                                                                                                                                                                                                      | Code : 200<br/><br/>Code : 404<br/>Content : "해당 일정이 존재하지 않습니다."                          | -                                                            |
| 단건 일정 수정  | PATCH  | /schedules/{id}                                                                                                     | {<br/>"name" : "작성자",<br/>"password" : "비밀번호",<br/>"title" : "제목",<br/>"contents" : "내용"<br/>} | {<br/>"id" : "1",<br/>"name" : "작성자",<br/>"title" : "제목",<br/>"contents" : "내용",<br/>"createdAt" : "2025-02-02T17:11:10.25361",<br/>"updatedAt" : "2025-02-02T17:11:10.25361"<br/>} | Code : 200<br/><br/>Code : 404<br/>Content : "해당 일정이 존재하지 않습니다.",<br/>"비밀번호를 잘못 입력하였습니다." | 일정 수정 시, 비밀번호 검증                                             | 
| 단건 일정 삭제  | DELETE | /schedules/{id}                                                                                                     | {<br/>"password" : "비밀번호"<br/>}                                            | -                                                                                                                                                                                                                                                                                                                                                                                        | Code : 200<br/><br/>Code : 404<br/>Content : "해당 일정이 존재하지 않습니다.",<br/>"비밀번호를 잘못 입력하였습니다." | 일정 삭제 시, 비밀번호 검증                                             |

### ERD

<img src="https://github.com/user-attachments/assets/451ce49d-f108-4d1b-b46d-22d9abd379ca">
