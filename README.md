# 키친포스

## 퀵 스타트

```sh
cd docker
docker compose -p kitchenpos up -d
```

## 요구 사항

- 식당 관리 프로그램을 구현한다.
- 상품
  - [x] 속성 
    - [x] 상품은 상품명과 가격을 가진다.
      - [x] 상품명을 반드시 지정해야 하며, 비속어를 포함되어서는 안된다.
      - [x] 가격은 0원 이상이어야 한다.
  - [x] use-case
    - [x] 상품을 등록할 수 있다. 
    - [x] 상품의 가격을 변경할 수 있다.
      - [x] 상품 가격이 변경되면 상품이 포함된 메뉴의 가격이 메뉴에 포함된 상품들의 총가격(단가 * 수량)보다 크다면
      해당 메뉴를 노출시키지 않는다.
    - [x] 모든 상품을 조회할 수 있다.
- 메뉴 그룹
  - [x] 속성 
    - [x] 메뉴 그룹은 이름을 가진다.
      - [x] 메뉴 그룹의 이름을 반드시 지정해야 한다.
  - [x] use-case
    - [x] 메뉴 그룹을 생성할 수 있다.
    - [x] 모든 메뉴 그룹을 조회할 수 있다.
- 메뉴
  - [x] 속성 
    - [x] 메뉴는 여러 개의 상품을 포함할 수 있다.
      - [x] 메뉴의 포함된 상품은 수량을 가진다.
      - [x] 수량은 0개 이상이어야 한다.
    - [x] 이름, 가격, 노출 여부, 메뉴 그룹을 가진다.
      - [x] 메뉴의 가격은 0원 이상이어야 한다.
      - [x] 메뉴 가격은 메뉴에 포함된 상품들의 총가격(단가 * 수량) 보다 작아야 한다.
      - [x] 메뉴는 반드시 메뉴 그룹에 포함되어야 한다.
  - [x] use-case
    - [x] 메뉴를 생성할 수 있다.
    - [x] 메뉴의 가격을 바꿀 수 있다.
    - [x] 메뉴를 노출할 수 있다.
      - [x] 메뉴 가격이 메뉴에 포함된 상품들의 총가격(단가 * 수량) 보다 작을 때만 노출할 수 있다.
    - [x] 모든 메뉴를 조회할 수 있다.
- 테이블
  - [ ] 속성
    - [ ] 테이블은 이름, 예약한 손님 수, 점유 여부를 가진다.
      - [ ] 테이블 이름을 반드시 지정해야 한다.
      - [ ] 손님 수는 0명 이상이다.
      - [ ] '점유되지 않은 상태'일 때는 손님 수가 0명이다.
  - [ ] use-case
    - [ ] 테이블을 생성할 수 있다.
      - [ ] 이 때, 테이블을 '점유되지 않은 상태'로 생성한다.
    - [ ] 테이블의 손님 수를 변경할 수 있다.
      - [ ] 점유된 테이블은 손님 수를 변경할 수 없다.
    - [ ] 테이블을 점유할 수 있다.
    - [ ] 테이블 점유를 해제할 수 있다.
      - [ ] 주문이 완료된 경우에만 점유를 해제할 수 있다.
      - [ ] 점유를 해제하면 테이블을 '점유되지 않은 상태'로 만든다.
    - [ ] 모든 테이블을 조회할 수 있다.
- 주문
  - [ ] 속성
    - [ ] 주문은 주문 유형, 상태, 주문 시간, 주문 품목들, 배송 주소, 주문 테이블 정보를 가진다.
      - [ ] 주문 유형은 총 세 가지다.
        - 배달
        - 포장
        - 매장 식사
      - [ ] 주문 상태는 총 여섯 가지다.
        - 대기 중
        - 접수 완료
        - 서빙 중
        - 배달 중
        - 배달 완료
        - 완료
    - [ ] 주문한 메뉴와 그 메뉴의 수량을 기록해야 한다.
    - [ ] 최소 한 개의 주문이 있어야 한다.
    - [ ] 매장 식사 주문은 경우, 테이블을 점유하고 주문에 테이블도 기록한다.
    - [ ] 배달 주문은 주소를 기록해야 한다.
  - [ ] use-case
    - [ ] 주문을 생성할 수 있다.
      - [ ] 새로 생성될 주문은 속성에 정의한 조건을 만족해야 한다.
      - [ ] 최초 주문 상태는 '대기 중'이다.
      - [ ] 주문 시간을 기록한다.
      - [ ] 메뉴에 없는 항목은 주문할 수 없다.
      - [ ] 배달 또는 포장 주문인 경우에는, 주문한 품목의 수량이 반드시 0개 이상이어야 한다.
    - [ ] 주문을 수락할 수 있다.
      - [ ] 대기중인 주문만 수락할 수 있다.
      - [ ] 배달 주문의 경우, 라이더스에 주문 번호, 가격, 배달 위치를 전달해야 한다.
      - [ ] 주문 상태를 '접수 완료' 으로 변경한다.
    - [ ] 주문 서빙을 할 수 있다.
      - [ ] '접수 완료' 상태의 주문만 서빙할 수 있다.
      - [ ] 주문 상태를 '서빙 중' 으로 변경한다.
    - [ ] 배달을 시작할 수 있다.
      - [ ] 배달 주문이고, '서빙 중' 상태일 때만 배달을 시작할 수 있다.
      - [ ] 주문 상태를 '배달 중' 으로 변경한다.
    - [ ] 배달 완료 처리할 수 있다.
      - [ ] 배달 주문이고, '배달 중' 상태일 때만 배달 완료 처리가 가능하다.
      - [ ] 주문 상태를 '배달 완료' 로 변경한다.
    - [ ] 주문을 완료 처리할 수 있다.
      - [ ] 배달 주문인 경우, '배달 완료' 인 상태일 때만 완료 처리할 수 있다.
      - [ ] 테이크아웃, 매장 식사 주문인 경우 '서빙 됨' 상태일 때만 완료 처리할 수 있다.
      - [ ] 매장 식사 주문을 완료 처리하면 테이블은 '점유되지 않은 상태'로 변경되어야 한다.
      - [ ] 주문 상태를 '완료' 로 변경한다.
    - [ ] 모든 주문을 조회할 수 있다.



## 용어 사전

| 한글명   | 영문명         | 설명                                  |
|-------|-------------|-------------------------------------|
| | |  |



## 모델링
