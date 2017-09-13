//
//  CHPublishServiceTableViewCell.h
//  shenbianapp
//
//  Created by book on 2017/9/10.
//  Copyright © 2017年 陈坚. All rights reserved.
//

#import <UIKit/UIKit.h>

@interface CHPublishServiceTableViewCell : UITableViewCell

@property(nonatomic,strong) UILabel *titleLabel;

@property(nonatomic,strong) NSArray *serviceKind;

@property(nonatomic,assign) NSUInteger index;

@end
