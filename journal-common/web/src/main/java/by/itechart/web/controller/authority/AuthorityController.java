package by.itechart.web.controller.authority;


import by.itechart.mapping.authority.AuthorityMapper;
import by.itechart.mapping.dto.authority.AuthorityDto;
import by.itechart.model.user.UserAuthority;
import by.itechart.service.authority.AuthorityService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import javax.validation.constraints.Min;
import java.net.URI;
import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/admin/roles")
public class AuthorityController {

    private final AuthorityService authorityService;

    private final AuthorityMapper mapper;


    @PostMapping("/role")
    public ResponseEntity<AuthorityDto> saveAuthority(@RequestBody
                                                      @Valid AuthorityDto authorityDto) {

        UserAuthority userAuthority = mapper.authorityDtoToUserAuthority(authorityDto);
        UserAuthority storedAuthority = authorityService.saveAuthority(userAuthority);
        AuthorityDto dto = mapper.userAuthorityToAuthorityDto(storedAuthority);

        return ResponseEntity.created(URI.create("/admin/roles/role/" + storedAuthority.getId()))
                                                                                            .body(dto);
    }

    @GetMapping("/role/{authorityId}")
    public ResponseEntity<AuthorityDto> getAuthorityById(@PathVariable("authorityId")
                                                         @Min(value = 1,
                                                              message = "The ID must be greater than 0")
                                                         Long authorityId) throws Throwable {

        UserAuthority authorityById = authorityService.getAuthorityById(authorityId);
        AuthorityDto dto = mapper.userAuthorityToAuthorityDto(authorityById);

        return ResponseEntity.ok(dto);
    }

    @GetMapping("/role")
    public ResponseEntity<AuthorityDto> getAuthorityByName(@RequestParam("name")
                                                           @Min(value = 1,
                                                                message = "The name must be longer that 1")
                                                           String name) throws Throwable {

        UserAuthority authorityByName = authorityService.getAuthorityByName(name);
        AuthorityDto dto = mapper.userAuthorityToAuthorityDto(authorityByName);

        return ResponseEntity.ok(dto);
    }

    @PutMapping("/role/{authorityId}")
    public ResponseEntity<AuthorityDto> updateAuthority(@PathVariable("authorityId")
                                                        @Min(value = 1,
                                                             message = "The ID must be greater than 0")
                                                        Long authorityId,
                                                        @RequestBody
                                                        @Valid AuthorityDto authorityDto)
                                                                                     throws Throwable {

        UserAuthority userAuthority = mapper.authorityDtoToUserAuthority(authorityDto);
        UserAuthority updatedUserAuthority = authorityService.updateAuthority(authorityId, userAuthority);
        AuthorityDto dto = mapper.userAuthorityToAuthorityDto(updatedUserAuthority);

        return ResponseEntity.ok(dto);
    }

    @DeleteMapping("/role/{authorityId}")
    public ResponseEntity<Void> deleteAuthorityById(@PathVariable("authorityId")
                                                    @Min(value = 1,
                                                         message = "The ID must be greater than 0")
                                                    Long authorityId) {

        authorityService.deleteAuthorityById(authorityId);

        return ResponseEntity.noContent()
                                        .build();
    }

    @DeleteMapping("/role")
    public ResponseEntity<Void> deleteAuthorityByName(@RequestParam("name")
                                                      @Min(value = 2,
                                                           message = "The name size must be greater than 2")
                                                      String name) {

        authorityService.deleteAuthorityByName(name);

        return ResponseEntity.noContent()
                                        .build();
    }

    @GetMapping
    public ResponseEntity<List<AuthorityDto>> getAllAuthority() {

        List<UserAuthority> authorities = authorityService.getAllAuthorities();
        List<AuthorityDto> dtoList = mapper.userAuthorityListToAuthorityDtoList(authorities);

        return ResponseEntity.ok(dtoList);
    }


}
