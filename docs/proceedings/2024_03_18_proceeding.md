### 회의록(주말간 과제 종합)

#### 회원
  1. 회원기본정보 - 회원 상세 정보 분리
     - 회원 기본정보 (회원 가입 데이터)
     - 회원 상세 정보()
  2. 회원 상세 정보 등급 설정 추가, 신고 횟수 추가
  3. 특성이 게시판임 만큼 회원에 대한 정지는 삭제하여 자유도를 완화하고 신고 누적시 영구 제한


#### 게시판
  1. 후기 게시판 작성에 대한 인증은 뻄
  2. 후기게시판 작성시 인증마크 부여, 포인트 지급 포인트지급양은 


#### 호스트
  1. 호스트 등록 신청 -> 숙소등록 신청으로 변경
  2. 게스트가 예약시 호스트가 승인, 거절 기능 추가
    - 관리자 숙소 거절 이유는 빼기 -> 너무 복잡함. 더 중요한 부분에 집중하기로.
  3. 숙소 관리페이지 - 호스트 숙소 운영기간 선택 폼 변경 
     - 이미 숙소를 운영중인경우 - 종료일만 설정
     - 이미 예약을 받아버린경우 - 종료일만 설정
     - 그외 시작일 , 종료일 모두 설정

#### 숙소
1. 숙소 상세 페이지 - 리뷰 별점 또는 평점 출력
   - 리뷰 중 가장 높은 평점의 리뷰 1~2개 출력
   - 예약하기 버튼 추가
    


#### Figma UiPrototype 변경사항
  1. 게시판 좌측 탭바 삭제
  2. 숙소 관리페이지 - 호스트 숙소 운영기간 선택 폼 변경
  3. 숙소 메인 - 통합 검색(필터링 추가 - 지역, 가격(1주일 기준), 편의 시설, 숙소 유형,)
  4. 지도 검색(검색하지 않을시 현재위치, 검색시 지역)
  5. 후기 메인 추천수 표시
  6. 회원가입 폼 
     - 전화번호를 빼고 이메일 인증 사용(숙소가 아니라 커뮤니티만 사용하는 회원은 전화번호가 불필요하기 때문-> 전화번호 미인증자는 숙소 예약 불가)
     - 닉네임 추가
  7. 숙소 상세 - 예약하기 버튼 추가


#### 만들어야할 UIPrototype
  1. 예약 상세페이지, 결제 페이지 만들기
  2. 게스트 - 예약하기, 결제하기 페이지 만들기
     - 이용완료 내역에 한해서는 리뷰 작성하기 버튼 출력
     - 체크인
     - 체크아웃
     - 호스트 정보(프로필, 이름, 전화번호)
     - 호스트에게 메세지
     - 예약 세부정보
       - 예약 번호
       - 환불정책
       - 숙소 주소
       - 숙소 메뉴얼 or 이용 규칙
     - 숙소보기로 이동
     - 총 요금
     - 결제상세 이동(팝업 or 새로운 페이지)
  3. 숙소 후기 작성, 보여주는 페이지(댓글형식 + 평점)
  4. 호스트 숙소 가격 변경
  5. 숙소 상세 페이지 - 리뷰 별점 또는 평점 출력
      - 리뷰 중 가장 높은 평점의 리뷰 1~2개 출력
      - 숙소 신고, 공유 UI 버튼 추가
  6. 커뮤니티 글작성 페이지
     - 글쓰기 폼 수정이 필요해 보임
     - 디테일적으로 UI 깔끔하게
  7. 아이디, 비밀번호 찾기
     - 해당 이메일이 존재하는지 여부만 확인
     - 이메일 인증해서 새 비밀번호 설정
  8. 회원정보 수정 - 닉네임 조회/변경 추가


  
#### 보류
  1. 채팅과 채팅 알람 표시
  2. 선호 사항
   



    
    
    
    