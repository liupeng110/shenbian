//
//  CHBaseViewCModel.m
//  shenbianapp
//
//  Created by book on 2017/8/27.
//  Copyright © 2017年 陈坚. All rights reserved.
//

#import "CHBaseViewCModel.h"

@implementation CHBaseViewCModel
- (id)initWithCoder:(NSCoder *)aDecoder {
    if (self = [super init]) {
        unsigned int outCount;
        Ivar * ivars = class_copyIvarList([self class], &outCount);
        for (int i = 0; i < outCount; i ++) {
            Ivar ivar = ivars[i];
            NSString * key = [NSString stringWithUTF8String:ivar_getName(ivar)];
            key = [key substringFromIndex:1];
            
            id value = [aDecoder decodeObjectForKey:key];
            
            if (value) {
                [self setValue:value forKey:key];
            } else {
                return nil;
            }
        }
    }
    return self;
}

- (void)encodeWithCoder:(NSCoder *)aCoder {
    unsigned int outCount;
    Ivar * ivars = class_copyIvarList([self class], &outCount);
    for (int i = 0; i < outCount; i ++) {
        Ivar ivar = ivars[i];
        NSString * key = [NSString stringWithUTF8String:ivar_getName(ivar)];
        key = [key substringFromIndex:1];
        [aCoder encodeObject:[self valueForKey:key] forKey:key];
    }
    
}
@end
