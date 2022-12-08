package com.example.service;

import com.example.model.User;

/**
 * Please add your description here.
 *
 * @author Pulse Innovations Ltd
 */
public class EmailVerificationServiceImpl implements EmailVerificationService
{
    @Override
    public void scheduleEmailConfirmation( final User user )
    {
        System.out.println("scheduleEmailConfirmation is called");
    }
}
