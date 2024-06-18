package org.nmfw.foodietree.domain.store.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.nmfw.foodietree.domain.store.dto.request.StoreSignUpDto;
import org.nmfw.foodietree.domain.store.entity.Store;
import org.nmfw.foodietree.domain.store.repository.StoreMapper;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
@Slf4j
public class StoreSignUpService {
    private final StoreMapper storeMapper;
    private final PasswordEncoder encoder;

    // 회원가입 중간 처리

    /**
     *
     * @param dto - store 회원가입시 입력되는 필수값 account, password만을 가진 dto
     * @return 정상적으로 tbl_store에 account와 암호화된 비밀번호가 저장되었다면 true반환
     */
    public boolean storeSignUp(StoreSignUpDto dto) {

        Store store = dto.toEntity();

        String encodedPassword = encoder.encode(dto.getPassword());
        store.setPassword(encodedPassword);

        return storeMapper.save(store);
    }
}
