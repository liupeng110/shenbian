//
//  CHMessageModel.h
//  shenbianapp
//
//  Created by book on 2017/10/28.
//  Copyright © 2017 . All rights reserved.
//



typedef NS_ENUM(NSInteger,CHMessageType) {
    MessageTypeChat,
    MessageTypeOrder,
    MessageTypeFocus
};

#import "CHBaseViewCModel.h"

@interface CHMessageModel : CHBaseViewCModel

@property(nonatomic,assign) CHMessageType messageType;
@property(nonatomic,copy)NSString *userName;
@property(nonatomic,copy)NSString *briefMessage;
@property(nonatomic,copy)NSString *lastTime;
@property(nonatomic,copy)NSString *headUrl;
@end
