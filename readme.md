# 쿠폰 선착순 이벤트 시스템

### 1. 문제점
- 쿠폰이 1000개 보다 많이 발급됨
- 이벤트 페이지에 접속이 안됨
- 이벤트와 상관없는 페이지가 느려짐

### 2. 문제 해결 과정
- 트래픽이 몰렸을 때 대처 방법
- redis 를 활용한 쿠폰 발급 개수 보장
- kafka 를 활용한 다른페이지에 대한 영양도 감소

### 3. 요구사항 정의
```text
선착순 1000명에게 할인쿠폰을 제공하는 이벤트를 진행한다.
이 이벤트는 아래와 같은 조건을 만족시켜야 한다.

1. 선착순 1000명에게만 지급되어야 한다.
2. 1000개 초과로 쿠폰이 지급돼서는 안된다.
3. 순간적으로 몰리는 트래픽을 버틸 수 있어야 한다. 
```