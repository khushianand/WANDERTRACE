package com.wandertrace.dto;
import jakarta.validation.constraints.*;
import java.util.UUID;
public final class AuthDtos { private AuthDtos(){} public record Credentials(@Email @NotBlank String email,@NotBlank @Size(min=12,max=128) String password){} public record Registration(@Email @NotBlank String email,@NotBlank @Size(min=2,max=120) String displayName,@NotBlank @Size(min=12,max=128) String password){} public record User(UUID id,String email,String displayName,String role){} }
