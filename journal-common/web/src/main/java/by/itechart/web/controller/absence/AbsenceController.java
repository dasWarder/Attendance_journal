package by.itechart.web.controller.absence;


import by.itechart.mapping.absence.AbsenceMapper;
import by.itechart.mapping.absence.AbsenceMapperWithId;
import by.itechart.mapping.dto.absence.AbsenceDto;
import by.itechart.model.Absence;
import by.itechart.service.absence.AbsenceService;
import lombok.RequiredArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import javax.validation.constraints.Min;
import java.net.URI;
import java.time.LocalDate;


@RestController
@RequestMapping("/absence")
@RequiredArgsConstructor
public class AbsenceController {

    private final AbsenceMapper mapper;

    private final AbsenceService absenceService;

    private final AbsenceMapperWithId customMapper;


    @PostMapping
    public ResponseEntity<AbsenceDto> saveAbsence(@RequestBody
                                                  @Valid AbsenceDto dto) {

        Absence absence = mapper.absenceDtoToAbsence(dto);
        Absence storedAbsence = absenceService.createAbsence(absence);
        AbsenceDto responseDto = mapper.absenceToAbsenceDto(storedAbsence);

        return ResponseEntity.created(URI.create("/absence/" + storedAbsence.getId()))
                                                                        .body(responseDto);
    }


    @GetMapping("/{absenceId}")
    public ResponseEntity<AbsenceDto> getAbsenceById(@PathVariable("absenceId")
                                                     @Min(value = 10000,
                                                          message = "The absence id must be greater that 10000")
                                                     Long absenceId) throws Throwable {

        Absence absenceById = absenceService.getAbsenceById(absenceId);
        AbsenceDto responseDtoById = mapper.absenceToAbsenceDto(absenceById);

        return ResponseEntity.ok(responseDtoById);
    }


    @GetMapping
    public ResponseEntity<AbsenceDto> getAbsenceByAbsenceDate(@RequestParam("date")
                                                              @DateTimeFormat(iso =
                                                                              DateTimeFormat.ISO.DATE)
                                                              LocalDate absenceDate) throws Throwable {

        Absence absenceByAbsenceDate = absenceService.getAbsenceByAbsenceDate(absenceDate);
        AbsenceDto responseDtoByAbsenceDate = mapper.absenceToAbsenceDto(absenceByAbsenceDate);

        return ResponseEntity.ok(responseDtoByAbsenceDate);
    }

    @PutMapping("/{absenceId}")
    public ResponseEntity<AbsenceDto> updateAbsence(@PathVariable("absenceId")
                                                    @Min(value = 10000,
                                                            message = "The absence id must be greater that 10000")
                                                    Long absenceId,
                                                    @RequestBody
                                                    @Valid AbsenceDto updateDto) throws Throwable {

        Absence validAbsence = customMapper.absenceDtoToAbsenceWithId(updateDto);
        Absence updatedAbsence = absenceService.updateAbsence(absenceId, validAbsence);
        AbsenceDto updatedResponseDto = mapper.absenceToAbsenceDto(updatedAbsence);

        return ResponseEntity.ok(updatedResponseDto);
    }

    @DeleteMapping("/{absenceId}")
    public ResponseEntity<Void> deleteAbsenceById(@PathVariable
                                                  @Min(value = 10000,
                                                          message = "The absence id must be greater that 10000")
                                                  Long absenceId) {

        absenceService.deleteAbsenceById(absenceId);

        return ResponseEntity.noContent()
                                         .build();
    }

    @DeleteMapping
    public ResponseEntity<Void> deleteAbsenceByAbsenceDate(@RequestParam("date")
                                                           @DateTimeFormat(iso =
                                                                   DateTimeFormat.ISO.DATE)
                                                           LocalDate absenceDate) {

        absenceService.deleteAbsenceByAbsenceDate(absenceDate);

        return ResponseEntity.noContent()
                                         .build();
    }

}
