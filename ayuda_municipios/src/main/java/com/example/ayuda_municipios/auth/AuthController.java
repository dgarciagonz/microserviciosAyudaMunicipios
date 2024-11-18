package com.example.ayuda_municipios.auth;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.example.ayuda_municipios.auth.dto.LoginDTO;
import com.example.ayuda_municipios.auth.dto.TokenResponseDTO;
import com.example.ayuda_municipios.usuarios.Usuario;
import com.example.ayuda_municipios.usuarios.UsuariosService;
import com.example.ayuda_municipios.usuarios.dto.UsuarioDTO;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;

import java.security.NoSuchAlgorithmException;
import java.util.Date;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@RestController
@RequestMapping("/auth")
@RequiredArgsConstructor
public class AuthController {
    private final UsuariosService usuariosService;

    @Value("${jwt.secret}")
    private String jwtSecret;

    @PostMapping("login")
    public TokenResponseDTO login(@RequestBody @Valid LoginDTO loginDTO) throws NoSuchAlgorithmException {
        Usuario usuario = usuariosService.login(loginDTO);
        return new TokenResponseDTO(getToken(usuario));
    }

    @PostMapping("registro")
    @ResponseStatus(HttpStatus.CREATED)
    public Usuario registro(@RequestBody @Valid UsuarioDTO u) throws NoSuchAlgorithmException {       
        return usuariosService.insert(u);
    }

    private String getToken(Usuario user) {
        Algorithm algorithm = Algorithm.HMAC256(jwtSecret);
        String token = JWT.create()
                .withIssuer("user")
                .withClaim("id", user.getId())
                .withIssuedAt(new Date(System.currentTimeMillis()))
                .withExpiresAt(new Date(System.currentTimeMillis() + (24 * 60 * 60 * 1000))) // Caduca en 1 dia
                .sign(algorithm);
        return token;
    }

}
