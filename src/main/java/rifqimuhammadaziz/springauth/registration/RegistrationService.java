package rifqimuhammadaziz.springauth.registration;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import rifqimuhammadaziz.springauth.appuser.AppUser;
import rifqimuhammadaziz.springauth.appuser.AppUserRole;
import rifqimuhammadaziz.springauth.appuser.AppUserService;

@Service
@AllArgsConstructor
public class RegistrationService {

    private final AppUserService appUserService;
    private final EmailValidator emailValidator;

    public String register(RegistrationRequest request) {
        boolean isValidEmail = emailValidator.test(request.getEmail());
        if (!isValidEmail) {
            throw new IllegalStateException("Email not valid");
        }

        return appUserService.signUpUser(
                new AppUser(
                        request.getFirstName(),
                        request.getLastName(),
                        request.getEmail(),
                        request.getPassword(),
                        AppUserRole.USER
                )
        );
    }
}
