package com.example.service;

import com.example.model.User;

/**
 * Please add your description here.
 *
 * @author Pulse Innovations Ltd
 */
public interface EmailVerificationService
{
    void scheduleEmailConfirmation( User user);
}
