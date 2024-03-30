package io.nbc.selectedseat.web.domain.member;

import io.nbc.selectedseat.domain.member.dto.CoinInfo;
import io.nbc.selectedseat.domain.member.service.CoinService;
import io.nbc.selectedseat.web.common.dto.ResponseDTO;
import io.nbc.selectedseat.web.domain.member.dto.CoinRequestDTO;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/coins")
@RequiredArgsConstructor
public class CoinController {

    private final CoinService coinService;

    @PutMapping("/charge")
    public ResponseEntity<ResponseDTO<CoinInfo>> chargeCoin(
        @Valid @RequestBody CoinRequestDTO requestDTO
        // Todo: user
    ) {
        CoinInfo responseDTO = coinService.chargeCoin(1L, requestDTO.amount());
        return ResponseEntity.status(HttpStatus.OK).body(
            ResponseDTO.<CoinInfo>builder()
                .statusCode(HttpStatus.OK.value())
                .message("코인 충전 성공")
                .data(responseDTO)
                .build());
    }

    @PutMapping("/deduction")
    public ResponseEntity<ResponseDTO<CoinInfo>> deductionCoin(
        @Valid @RequestBody CoinRequestDTO requestDTO
        // Todo: user
    ) {
        CoinInfo responseDTO = coinService.deductionCoin(1L, requestDTO.amount());
        return ResponseEntity.status(HttpStatus.OK).body(
            ResponseDTO.<CoinInfo>builder()
                .statusCode(HttpStatus.OK.value())
                .message("코인 차감 성공")
                .data(responseDTO)
                .build());
    }
}
